package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.userexception.ProjectCantFindException;
import com.example.tumblbugclone.Exception.userexception.UnregisterUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.managedconst.ProjectConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;

@Slf4j
public class ProjectCardService {
    UserRepository userRepository = UserRepository.getUserRepository();
    ProjectRepository projectRepository = ProjectRepository.getProjectRepository();

    public ArrayList<ProjectCard> findOngoingFromStart() throws ParseException {

/*        String today = getTodayString();
        ArrayList<ProjectCard> projectCards = new ArrayList<>();
        long projectId = 1l;*/

        return findOngoingFromIdx(1l);
        /*while(projectCards.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE) {
            try {
                Project project = projectRepository.findProjectById(projectId);
                String projectStartDate = project.getStartDate();
                String projectEndDate = project.getEndDate();

                if(Callendar.after(today, projectEndDate)) {
                    projectId++;
                    continue;
                }
                if(Callendar.before(today, projectStartDate)){
                    projectId++;
                    continue;
                }
                User creater = findCreater(project);
                ProjectCard card = new ProjectCard(project, creater);

                log.info("add project Idx {}", projectId);
                projectCards.add(card);
                projectId++;

            } catch (ProjectCantFindException e) {
                return projectCards;
            } catch (UserCantFindException e) {
                projectId++;
            } catch (UnregisterUserException e) {
                projectId++;
            } catch (ParseException e) {
                e.printStackTrace();
                throw e;
            }
        }

        return projectCards;*/
    }

    public ArrayList<ProjectCard> findOngoingFromIdx(long startIdx) throws ParseException {
        String today = getTodayString();
        ArrayList<ProjectCard> projectCards = new ArrayList<>();
        long projectId = startIdx;

        while(projectCards.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE) {
            try {

                Project project = projectRepository.findProjectById(projectId);
                String projectStartDate = project.getStartDate();
                String projectEndDate = project.getEndDate();

                if(Callendar.after(today, projectEndDate)) {
                    projectId++;
                    continue;
                }
                if(Callendar.before(today, projectStartDate)){
                    projectId++;
                    continue;
                }
                User creater = findCreater(project);
                ProjectCard card = new ProjectCard(project, creater);

                log.info("add project Idx {}", projectId);
                projectCards.add(card);
                projectId++;

            } catch (ProjectCantFindException e) {
                return projectCards;
            } catch (UserCantFindException e) {
                projectId++;
            } catch (UnregisterUserException e) {
                projectId++;
            } catch (ParseException e) {
                e.printStackTrace();
                throw e;
            }
        }

        return projectCards;
    }

    public ArrayList<ProjectCard> findPreLaunchingFromStart() throws ParseException {
        return findPreLaunchingFromIdx(1l);
    }

    public ArrayList<ProjectCard> findPreLaunchingFromIdx(long startIdx) throws ParseException {
        String today = getTodayString();
        ArrayList<ProjectCard> projectCards = new ArrayList<>();
        long projectId = startIdx;

        while(projectCards.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE) {
            try {

                Project project = projectRepository.findProjectById(projectId);
                String projectStartDate = project.getStartDate();

                if(Callendar.after(today, projectStartDate)){
                    projectId++;
                    continue;
                }
                User creater = findCreater(project);
                ProjectCard card = new ProjectCard(project, creater);

                log.info("add project Idx {}", projectId);
                projectCards.add(card);
                projectId++;

            } catch (ProjectCantFindException e) {
                return projectCards;
            } catch (UserCantFindException e) {
                projectId++;
            } catch (UnregisterUserException e) {
                projectId++;
            } catch (ParseException e) {
                e.printStackTrace();
                throw e;
            }
        }

        return projectCards;
    }
    private String getTodayString() {
        return Callendar.getTodayString();
    }

    private User findCreater(Project project) throws UserCantFindException, UnregisterUserException {
        Long userIdx = project.getUserIdx();
        User creater = userRepository.findUserByIdx(userIdx);
        return creater;
    }
}
