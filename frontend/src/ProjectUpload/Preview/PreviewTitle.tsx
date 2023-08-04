import React from 'react';
import { $, setElementVisible } from '../../utils/commonFunction';
import './previewTitle.scss';

const updatePreviewCategory = (categoryName:string) => {
  const $category = $(".previewTitle__category") as HTMLElement;
  $category.innerHTML = categoryName;
  setElementVisible($category);
}

const updatePreviewTitle = (previewTitle:string) => {
  const $previewTitle = $(".previewTitle__projectTitle") as HTMLElement;
  $previewTitle.innerHTML = previewTitle;
  setElementVisible($previewTitle);
}

const updatePreviewExplain = (previewExplain:string) => {
  const $previewExplain = $(".previewTitle__projectExplain") as HTMLElement;
  $previewExplain.innerHTML = previewExplain;
  setElementVisible($previewExplain);
}

function PreviewTitle() {
  return (
    <section className="previewTitle">
      <h4 className="previewTitle__category">디지털 제품</h4>
      <h1 className="previewTitle__projectTitle">
        폭염에 강한 급속냉각 아이스 핸디 에어컨[쿨링 핸디팬]
      </h1>
      <span className="previewTitle__projectExplain">아이스 핸디 에어컨을 판매합니다</span>
    </section>
  );
}

export default PreviewTitle;
export { updatePreviewCategory, updatePreviewTitle, updatePreviewExplain };