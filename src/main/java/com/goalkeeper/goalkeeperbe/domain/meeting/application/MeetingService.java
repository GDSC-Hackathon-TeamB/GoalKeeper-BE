package com.goalkeeper.goalkeeperbe.domain.meeting.application;

import com.goalkeeper.goalkeeperbe.domain.matching.domain.Man;
import com.goalkeeper.goalkeeperbe.domain.matching.domain.Person;
import com.goalkeeper.goalkeeperbe.domain.matching.domain.Woman;
import com.goalkeeper.goalkeeperbe.domain.meeting.domain.OrganizationRoom;
import com.goalkeeper.goalkeeperbe.domain.meeting.repository.OrganizationRoomRepository;
import com.goalkeeper.goalkeeperbe.domain.meeting.repository.PersonalRoomRepository;
import com.goalkeeper.goalkeeperbe.domain.users.domain.Gender;
import com.goalkeeper.goalkeeperbe.domain.users.domain.Prefer;
import com.goalkeeper.goalkeeperbe.domain.users.repository.PreferRepository;
import com.goalkeeper.goalkeeperbe.domain.users.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {
    private final PreferRepository preferRepository;
    private final OrganizationRoomRepository organizationRoomRepository;
    private final UserRepository userRepository;
    private List<Man> men = new ArrayList<>();
    private List<Woman> women = new ArrayList<>();
    @Autowired
    public MeetingService(PreferRepository personRepository, OrganizationRoomRepository organizationRoomRepository, UserRepository userRepository) {
        this.preferRepository = personRepository;
        this.organizationRoomRepository = organizationRoomRepository;
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
            if (menGroup != null) {
                for (Man man : menGroup.values()) {
                    System.out.println(man.getName());
                }
            } else {
                System.out.println("남자 그룹이 없습니다.");
            }

            System.out.println(mood + " 그룹 여자:");
            if (womenGroup != null) {
                for (Woman woman : womenGroup.values()) {
                    System.out.println(woman.getName());
                }
            } else {
                System.out.println("여자 그룹이 없습니다.");
            }
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
            if (!groups.containsKey(person.getPreferOrganization())) {
                groups.put(person.getPreferOrganization(), new HashMap<>());
            }
            groups.get(person.getPreferOrganization()).put(person.getName(), person);
        }
        return groups;
    }
}
