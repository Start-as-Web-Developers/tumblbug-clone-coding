import React from 'react';
import ProjectRegisterColumn from "./Register/ProjectRegisterColumn";
import "./projectUpload.scss";
import ProjectPreviewColumn from "./Preview/ProjectPreviewColumn";
import CategorySelectModal from './Register/CategorySelectModal';
import CardAddModal from './Register/CardAddModal';

function ProjectUpload() {
  return (
    <section className="projectUpload">
      <ProjectRegisterColumn />
      <ProjectPreviewColumn />
      <CategorySelectModal />
      <CardAddModal/>
    </section>
  );
}

export default ProjectUpload;
