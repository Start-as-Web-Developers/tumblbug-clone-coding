package com.example.tumblbugclone.service;

import com.example.tumblbugclone.model.Like;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    /*
    * 사용자 정보는 어떻게 받아오나?
    * 즉, 세션은 어떤 정보를 저장하고 있어야 하고, 어느 부분에서 사용자 엔티티를 받아와야 하나?
    *
    * 현재의 내 생각 : 세션 ID - 사용자 Index로 매핑되는 세션 ID 값을 쿠키로 전송 받는다.
    * Controller에서 쿠키에 존재하는 세션 Id를 Service에 넘겨 준다.
    * Service에서 세션 Id를 이용해 사용자 Idx를, 사용자 Idx로 사용자 Entity를 반환 받는다.
    *   해당 부분은 SessionService, SessionRepository로 분기 될 수 있음
    * Service에서 사용자 Entity를 이용한다.*/

    public long like(){
        return 0l;
    }

    public void dislike(){}

    public int countProjectLike(){
        return 0;
    }

    public List<Like> getUserLikes(){
        return new ArrayList<>();
    }

 
}
