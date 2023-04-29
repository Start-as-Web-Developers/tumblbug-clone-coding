import React from 'react';
import ProjectRegisterColumn from './ProjectRegisterColumn';
import "./projectUpload.scss";
import ProjectPreviewColumn from './ProjectPreviewColumn';

function ProjectUpload() {
  return (
    <section className="projectUpload">
      <ProjectRegisterColumn/>
      <ProjectPreviewColumn />
    </section>
  );
}

export default ProjectUpload;
