import React from 'react';
import "./projectDetail.scss";
import Navbar from '../Navbar/Navbar';
import ProjectDetailHeader from './ProjectDetailHeader';
import ProjectDetailContent from './ProjectDetailContent';

function ProjectDetail() {
    return (
        <section className="projectDetailContainer">
            <Navbar/>
            <ProjectDetailHeader/>
            <ProjectDetailContent/>
        </section>
    )
}

export default ProjectDetail;