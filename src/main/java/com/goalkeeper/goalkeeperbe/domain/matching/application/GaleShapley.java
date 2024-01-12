package com.goalkeeper.goalkeeperbe.domain.matching.application;

import com.goalkeeper.goalkeeperbe.domain.matching.domain.Man;
import com.goalkeeper.goalkeeperbe.domain.matching.domain.Woman;
import com.goalkeeper.goalkeeperbe.domain.meeting.domain.PersonalRoom;
import com.goalkeeper.goalkeeperbe.domain.meeting.repository.PersonalRoomRepository;
import com.goalkeeper.goalkeeperbe.domain.users.domain.User;
import com.goalkeeper.goalkeeperbe.domain.users.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class GaleShapley {
    private final int N;
    private int engagedCount;
    private Map<String, Man> men;
    private Map<String, Woman> women;
    private final PersonalRoomRepository personalRoomRepository;
    private final UserRepository userRepository;
    // 생성자
    public GaleShapley(Map<String, Man> m, Map<String, Woman> w, PersonalRoomRepository personalRoomRepository, UserRepository userRepository) {
        this.personalRoomRepository = personalRoomRepository;
        this.userRepository = userRepository;
        N = Math.min(m.size(), w.size());
        engagedCount = 0;
        men = m;
        women = w;
        for (Man man : men.values()) {
            man.setPreferences(new ArrayList<>(man.getPreferences()));
        }
        for (Woman woman : women.values()) {
            woman.setPreferences(new ArrayList<>(woman.getPreferences()));
        }
        calcMatches();
    }

    private void calcMatches() {
        System.out.println("매칭을 계산합니다.");
        firstPropose();
        firstAcceptOrReject();
        while (engagedCount < N) {
            Man free = null;
            for (Man man : men.values()) {
                if (!man.isEngaged()) {
                    free = man;
                    break;
                }
            }

            if (free != null) {
                System.out.println("남자 " + free.getName() + "이(가) 매칭을 시도합니다.");

                for (String w : free.getPreferences()) {
                    Woman woman = women.get(w);
                    if (!free.isEngaged() && !free.getProposed().contains(woman)) {
                        System.out.println("   " + free.getName() + "이(가) " + woman.getName() + "에게 고백합니다.");
                        free.getProposed().add(woman);

                        if (woman.getPartner() == null) {
                            woman.setPartnerUserId(free.getUser().getUserId());
                            woman.setPartner(free.getName());
                            free.setEngaged(true);
                            engagedCount++;
                            System.out.println("   " + woman.getName() + "은(는) " + free.getName() + "과(와) 매칭되었습니다.");

                        } else {
                            Man currentPartner = men.get(woman.getPartner());
                            System.out.println("   " + woman.getName() + "은(는) 이미 " + currentPartner.getName() + "과(와) 매칭되어 있습니다.");

                            if (morePreference(currentPartner, free, woman)) {
                                woman.setPartnerUserId(free.getUser().getUserId());
                                woman.setPartner(free.getName());
                                free.setEngaged(true);
                                currentPartner.setEngaged(false);
                                System.out.println("   " + woman.getName() + "은(는) " + free.getName() + "과(와) 매칭되었습니다. (이전 매칭 해제)");
                            } else {
                                System.out.println("   " + woman.getName() + "은(는) 여전히 " + currentPartner.getName() + "과(와) 매칭되어 있습니다. (새로운 남자 선호도 부족)");
                            }
                        }
                    }
                }
            }
        }
        printCouples();
    }

    private void firstPropose() {
        System.out.println("모든 남자가 첫 번째 선호도의 여성에게 고백합니다.");
        for (Man man : men.values()) {
            Woman woman = women.get(man.getPreferences().get(0));
            woman.getProposals().add(man);
            man.getProposed().add(woman);  // 남자가 고백한 여성을 기록
            System.out.println(man.getName() + "이(가) " + woman.getName() + "에게 고백했습니다.");
        }
    }

    private void firstAcceptOrReject() {
        System.out.println("각 여성이 고백한 남자 중 가장 선호하는 남자를 선택합니다.");
        for (Woman woman : women.values()) {
            if (!woman.getProposals().isEmpty()) {
                Man chosenMan = woman.getProposals().get(0);
                for (Man man : woman.getProposals()) {
                    if (woman.getPreferences().indexOf(man.getName()) < woman.getPreferences().indexOf(chosenMan.getName())) {
                        chosenMan = man;
                    }
                }
                woman.setPartnerUserId(chosenMan.getUser().getUserId());
                woman.setPartner(chosenMan.getName());
                chosenMan.setEngaged(true);
                engagedCount++;
                System.out.println(woman.getName() + "이(가) " + chosenMan.getName() + "을(를) 선택했습니다.");

                woman.getProposals().remove(chosenMan);
                for (Man man : woman.getProposals()) {
                    man.setEngaged(false);
                    man.getPreferences().remove(woman.getName());
                }
                woman.getProposals().clear();
            }
        }
    }

    public boolean morePreference(Man curPartner, Man newPartner, Woman w) {
        for (String m : w.getPreferences()) {
            if (m.equals(newPartner.getName()))
                return true;
            if (m.equals(curPartner.getName()))
                return false;
        }
        return false;
    }

    public void printCouples() {
        System.out.println("----------------------------------------");
        System.out.println("--------------- 최종결과 ---------------");
        System.out.println("----------------------------------------");
        for (Woman w : women.values()) {
            System.out.println(w.getName() + " - " + w.getPartner());

            if (w.getUserId() != null && w.getPartnerUserId() != null) {
                Optional<User> womenUser = userRepository.findById(w.getUserId());
                Optional<User> partnerUser = userRepository.findById(w.getPartnerUserId());

                if (womenUser.isPresent() && partnerUser.isPresent()) {
                    PersonalRoom personalRoom = PersonalRoom.builder()
                            .user1(womenUser.get())
                            .user2(partnerUser.get())
                            .createdAt(LocalDateTime.now())
                            .build();
                    personalRoomRepository.save(personalRoom);
                }
            }
        }
    }
}