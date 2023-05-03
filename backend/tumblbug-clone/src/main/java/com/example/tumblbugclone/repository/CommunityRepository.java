package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.communityException.CommunityCantFindException;
import com.example.tumblbugclone.Exception.userexception.UnregisterUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.model.Community;
import com.example.tumblbugclone.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityRepository {

    private static Long id = 0L;
    private static final CommunityRepository communityRepository = new CommunityRepository();
    private static HashMap<Long, Community> communityDB = new HashMap<>();
    private static UserRepository userDB = UserRepository.getUserRepository();

    private CommunityRepository() {};

    public static CommunityRepository getCommunityRepository() {return communityRepository; }

    public void clear() {
        id = 0L;
        communityDB.clear();
    }

    public long save(Community community) throws Exception {
        // 테스트를 위한 임시 user 삽입
        userDB.clear();
        User user = new User("hi", "hi", "hi", "hi");
        userDB.save(user);
        community.setUserIdx(1);

        id++;
        community.setCommunityId(id);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayTime = now.format(formatter);
        community.setWriteDate(todayTime);
        community.setWriteDate(todayTime);

        communityDB.put(id, community);
        return id;
    }

    public void verifyCommunityIdx(Long communityIdx) throws CommunityCantFindException {

        if(communityIdx == null)
            throw new CommunityCantFindException();

        if(communityIdx > id)
            throw new CommunityCantFindException();

        if(!communityDB.containsKey(communityIdx))
            throw new CommunityCantFindException();

    }

    public Community findCommunityById(Long requiredId) throws CommunityCantFindException {
        verifyCommunityIdx(requiredId);
        return communityDB.get(requiredId);
    }

    public long updateCommunity(Community updateCommunity) throws Exception {
        // 테스트를 위한 임시 user 삽입
        userDB.clear();
        User user = new User("hi", "hi", "hi", "hi");
        userDB.save(user);
        updateCommunity.setUserIdx(1);

        long communityIdx = updateCommunity.getCommunityId();
        verifyCommunityIdx(communityIdx);

        communityDB.put(communityIdx, updateCommunity);
        return communityIdx;
    }

    public void deleteCommunity(Long communityIdx) throws CommunityCantFindException {
        verifyCommunityIdx(communityIdx);
        communityDB.remove(communityIdx);
    }

    public List<Map<String, Object>> findCommunityByProjectId(Long projectIdx) throws JsonProcessingException {
        // 프로젝트 로직 추가 시 projectIdx 유효성 검사 코드 추가 예정

        List<Map<String, Object>> communityList = new ArrayList<>();

        // communityDB에서 projectId에 해당하는 Community 객체들을 찾아서 리스트에 추가
        communityDB.forEach((key, value) -> {
            if (value.getProjectId() == projectIdx) {
                Map<String, Object> communityMap = new HashMap<>();
                User user = null;
                try {
                    user = userDB.findUserByIdx(value.getUserIdx());
                } catch (UserCantFindException e) {
                    throw new RuntimeException(e);
                } catch (UnregisterUserException e) {
                    throw new RuntimeException(e);
                }
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("name", user.getUserName());
                userMap.put("image", user.getUserImg());
                userMap.put("userIdx", user.getUserIdx());
                communityMap.put("user", userMap);
                communityMap.put("comment", value.getComment());
                communityMap.put("writeDate", value.getWriteDate());
                communityMap.put("communityIdx", value.getCommunityId());
                communityList.add(communityMap);
            }
        });

        return communityList;

    }

}
