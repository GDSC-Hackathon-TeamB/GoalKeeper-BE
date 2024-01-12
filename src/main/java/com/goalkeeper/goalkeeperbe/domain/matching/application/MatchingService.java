package com.goalkeeper.goalkeeperbe.domain.matching.application;


import com.goalkeeper.goalkeeperbe.domain.matching.domain.Man;
import com.goalkeeper.goalkeeperbe.domain.matching.domain.Person;
import com.goalkeeper.goalkeeperbe.domain.matching.domain.Woman;
import com.goalkeeper.goalkeeperbe.domain.meeting.repository.PersonalRoomRepository;
import com.goalkeeper.goalkeeperbe.domain.users.domain.Gender;
import com.goalkeeper.goalkeeperbe.domain.users.domain.Prefer;
import com.goalkeeper.goalkeeperbe.domain.users.repository.PreferRepository;
import com.goalkeeper.goalkeeperbe.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class MatchingService {
    private final PreferRepository preferRepository;
    private final PersonalRoomRepository personalRoomRepository;
    private final UserRepository userRepository;
    private List<Man> men = new ArrayList<>();
    private List<Woman> women = new ArrayList<>();
    @Autowired
    public MatchingService(PreferRepository personRepository, PersonalRoomRepository personalRoomRepository, UserRepository userRepository) {
        this.preferRepository = personRepository;
        this.personalRoomRepository = personalRoomRepository;
        this.userRepository = userRepository;
    }
    public void match() {
        List<Prefer> activeMatchTrue = preferRepository.findByUserGender(Gender.MALE);
        activeMatchTrue.addAll(preferRepository.findByUserGender(Gender.FEMALE));
        for (Prefer prefer : activeMatchTrue) {
            Person person = new Person(prefer.getUser(), prefer);
            if (person.getGender() == Gender.MALE) {
                men.add(new Man(person.getUser(), person.getPrefer()));
            } else if (person.getGender() == Gender.FEMALE) {
                women.add(new Woman(person.getUser(), person.getPrefer()));
            }
        }
    }
    public void grouping() {
        Map<String, Man> m = convertListToMap(men);
        Map<String, Woman> w = convertListToMap(women);

        Map<String, Map<String, Man>> menGroups = groupByMood(m);
        Map<String, Map<String, Woman>> womenGroups = groupByMood(w);

        for (String mood : menGroups.keySet()) {
            Map<String, Man> menGroup = menGroups.get(mood);
            Map<String, Woman> womenGroup = womenGroups.get(mood);

            System.out.println();
            System.out.println("====================================");
            System.out.println(mood + " 그룹 매칭을 시작합니다.");
            System.out.println("====================================");

            System.out.println(mood + " 그룹 남자:");
            for (Man man : menGroup.values()) {
                System.out.println(man.getName());
            }
            System.out.println(mood + " 그룹 여자:");
            for (Woman woman : womenGroup.values()) {
                System.out.println(woman.getName());
            }

            for (Man man : menGroup.values()) {
                List<String> preferences = new ArrayList<>(womenGroup.keySet());
                Collections.shuffle(preferences);

                man.setPreferences(preferences);
                System.out.println(man.getName() + "의 선호도 목록 : " + preferences);
            }

            for (Woman woman : womenGroup.values()) {
                List<String> preferences = new ArrayList<>(menGroup.keySet());
                Collections.shuffle(preferences);

                woman.setPreferences(preferences);
                System.out.println(woman.getName() + "의 선호도 목록 : " + preferences);
            }

            new GaleShapley(menGroup, womenGroup, personalRoomRepository, userRepository);

        }
    }
    private <T extends Person> Map<String, T> convertListToMap(List<T> list) {
        Map<String, T> map = new LinkedHashMap<>();
        for (T person : list) {
            map.put(String.valueOf(person.getName()), person);
        }
        return map;
    }
    private static <T extends Person> Map<String, Map<String, T>> groupByMood(Map<String, T> persons) {
        Map<String, Map<String, T>> groups = new HashMap<>();
        for (T person : persons.values()) {
            if (!groups.containsKey(person.getPreferPersonal())) {
                groups.put(person.getPreferPersonal(), new HashMap<>());
            }
            groups.get(person.getPreferPersonal()).put(person.getName(), person);
        }
        return groups;
    }
}