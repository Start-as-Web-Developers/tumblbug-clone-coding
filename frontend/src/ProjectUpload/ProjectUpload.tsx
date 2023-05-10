import React, { useEffect } from 'react';
import ProjectRegisterColumn from "./Register/ProjectRegisterColumn";
import ProjectPreviewColumn from "./Preview/ProjectPreviewColumn";
import CategorySelectModal from './Register/CategorySelectModal';
import CardAddModal from './Register/CardAddModal';
import { $, changeCSS } from '../utils/commonFunction';
import { updatePreviewThumbNail } from './Preview/PreviewSummary';
import "./projectUpload.scss";

function ProjectUpload() {
  useEffect(() => {
    const $projectUploadContainer = $(".projectUpload") as HTMLElement;
    const $projectUploadModal = $(".projectUploadModal") as HTMLElement;

    $projectUploadModal.addEventListener("dragleave", (event) => {
      changeCSS($projectUploadModal, "display", "none");
    })

    $projectUploadContainer.addEventListener("dragover", (event) => {
      // prevent downloading files
      event.preventDefault();
      changeCSS($projectUploadModal, "display", "block");
    });

    $projectUploadContainer.addEventListener("drop", (event) => {
      // prevent open files
      event.preventDefault();
      changeCSS($projectUploadModal, "display", "none");

      const imageTypeReg = /\.(jpg|jpeg|png|gif)$/i
      const file = event.dataTransfer?.files[0];
      
      if(file && imageTypeReg.test(file.name)) {
        const dropFileReader = new FileReader();

        dropFileReader.onload = (e) => {
          const fileName = file.name;
          const imageLink = e.target?.result as string;

          const $thumbNailInput = $(".imageLinkRow > input") as HTMLInputElement;
          $thumbNailInput.value = fileName;
          updatePreviewThumbNail(imageLink);
        }

        dropFileReader.readAsDataURL(file);
      }
    })

  }, []);

  return (
    <section className="projectUpload">
      <ProjectRegisterColumn />
      <ProjectPreviewColumn />
      <CategorySelectModal />
      <CardAddModal/>
      <section className="projectUploadModal"> </section>
    </section>
  );
}

export default ProjectUpload;
