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

    public ArrayList<ProjectCard> findFirstOngoingProject() throws ParseException {
        String today = Callendar.getTodayString();
        ArrayList<ProjectCard> projectCards = new ArrayList<>();
        long projectId = 1l;

        while(projectCards.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE) {
            try {
                log.info("search project Idx {}", projectId);

                Project project = projectRepository.findProjectById(projectId);
                String projectEndDate = project.getEndDate();

                if(Callendar.after(today, projectEndDate)) {
                    projectId++;
                    continue;
                }
                Long userIdx = project.getUserIdx();
                User creater = userRepository.findUserByIdx(userIdx);
                ProjectCard card = new ProjectCard(project, creater);

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

    public ArrayList<ProjectCard> findFirstEndProject() throws ParseException {
        String today = Callendar.getTodayString();
        ArrayList<ProjectCard> projectCards = new ArrayList<>();
        long projectId = 1l;

        while(projectCards.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE) {
            try {

                Project project = projectRepository.findProjectById(projectId);
                String projectEndDate = project.getEndDate();

                if(Callendar.after(today, projectEndDate) == false) {
                    projectId++;
                    continue;
                }

                log.info("add project Idx {}", projectId);
                Long userIdx = project.getUserIdx();
                User creater = userRepository.findUserByIdx(userIdx);
                ProjectCard card = new ProjectCard(project, creater);

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
}
