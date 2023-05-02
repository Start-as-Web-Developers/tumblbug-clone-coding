import React from 'react';
import PreviewDetail from './PreviewDetail';
import PreviewSummary from './PreviewSummary';
import PreviewTitle from './PreviewTitle';
import "./previewContainer.scss";

function PreviewContainer() {
  return (
    <section className="previewContainer">
      <PreviewTitle/>
      <PreviewSummary/>
      <PreviewDetail/>
    </section>
  )
}

export default PreviewContainer;