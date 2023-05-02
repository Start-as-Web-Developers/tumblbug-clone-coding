import React from 'react';
import PreviewContainer from './PreviewContainer';
import "./projectPreviewColumn.scss";

function ProjectPreviewColumn() {
  return (
    <section className="projectPreviewColumn">
      <h1 className="projectPreviewColumn__title">Preview</h1>
      <PreviewContainer/>
    </section>
  );
}

export default ProjectPreviewColumn;