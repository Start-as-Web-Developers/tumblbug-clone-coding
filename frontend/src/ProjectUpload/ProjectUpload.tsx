import React from 'react';
import ProjectRegisterColumn from './ProjectRegisterColumn';
import "./projectUpload.scss";
import ProjectPreviewColumn from './ProjectPreviewColumn';
import CategorySelectModal from './CategorySelectModal';
import CardAddModal from './CardAddModal';

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
