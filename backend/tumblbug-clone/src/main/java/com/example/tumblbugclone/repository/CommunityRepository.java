package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.communityException.CommunityCantFindException;
import com.example.tumblbugclone.model.Community;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommunityRepository {

    private static Long id = 0L;
    private static final CommunityRepository communityRepository = new CommunityRepository();
    private static HashMap<Long, Community> communityDB = new HashMap<>();

    private CommunityRepository() {};

    public static CommunityRepository getCommunityRepository() {return communityRepository; }

    public void clear() {
        id = 0L;
        communityDB.clear();
    }

    public long save(Community community) {
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

    public long updateCommunity(Community updateCommunity) throws CommunityCantFindException {
        long communityIdx = updateCommunity.getCommunityId();
        verifyCommunityIdx(communityIdx);

        communityDB.put(communityIdx, updateCommunity);
        return communityIdx;
    }

    public void deleteCommunity(Long communityIdx) throws CommunityCantFindException {
        verifyCommunityIdx(communityIdx);
        communityDB.remove(communityIdx);
    }

    public List<Community> findCommunityByProjectId(Long projectIdx) {
        // 프로젝트 로직 추가 시 projectIdx 유효성 검사 코드 추가 예정

        List<Community> communityList = new ArrayList<Community>();

        communityDB.forEach((key, value) -> {
            if(value.getProjectId() == projectIdx)
                communityList.add(value);
        });

        return communityList;
    }

}
