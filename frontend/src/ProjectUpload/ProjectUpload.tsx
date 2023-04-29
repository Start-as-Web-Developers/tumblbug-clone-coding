import React from 'react';
import ProjectRegisterColumn from './ProjectRegisterColumn';
import "./projectUpload.scss";
import ProjectPreviewColumn from './ProjectPreviewColumn';
import CategorySelectModal from './CategorySelectModal';

function ProjectUpload() {
  return (
    <section className="projectUpload">
      <ProjectRegisterColumn />
      <ProjectPreviewColumn />
      <CategorySelectModal />
    </section>
  );
}

export default ProjectUpload;
