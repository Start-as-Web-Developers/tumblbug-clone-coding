import React, { useState, useEffect } from "react";
import { FiChevronDown } from "react-icons/fi";
import './bodyFilterBtnArea.scss';
import { HiX } from "react-icons/hi";
import { EDITOR_FILTER_BTN, ACHIEVEMENT_BTN } from "../utils/commonVariable";
import { $, changeCSS, changeMultiCSS, eventTo } from "../utils/commonFunction";

function BodyFilterBtnArea() {
  const [editorRecommending, setEditorRecommending] = useState(false);
  const [filterBoxOpen, setFilterBoxOpen] = useState(false);

  useEffect(() => {
    const $editorBtn = $(".filterBtnArea__editorRecommendBtn") as HTMLElement;
    const $xBtn = $editorBtn.querySelector("svg") as SVGSVGElement;

    function editorRecommendFilter(): void {
      if (editorRecommending) {
        changeMultiCSS($editorBtn, [
          {
            key: "width",
            cssValue: EDITOR_FILTER_BTN.ORIGINAL_WIDTH,
          },
          {
            key: "background-color",
            cssValue: EDITOR_FILTER_BTN.ORIGINAL_BACKGROUNDCOLOR,
          },
        ]);
        changeCSS($xBtn, "display", EDITOR_FILTER_BTN.ORIGINAL_DISPLAY);
      } else {
        changeMultiCSS($editorBtn, [
          {
            key: "width",
            cssValue: EDITOR_FILTER_BTN.CLICKED_WIDTH,
          },
          {
            key: "background-color",
            cssValue: EDITOR_FILTER_BTN.CLICKED_BACKGROUNDCOLOR,
          },
        ]);
        changeCSS($xBtn, "display", EDITOR_FILTER_BTN.CLICKED_DISPLAY);
      }
      setEditorRecommending(!editorRecommending);
    }

    eventTo($editorBtn, editorRecommendFilter);
  }, [editorRecommending]);

  useEffect(() => {
    const $filterBtn = $(".filterBtnArea__percentBtn") as HTMLElement;
    const $filterBox = $(".filterBox") as HTMLElement;

    eventTo($filterBtn, () => {
      if(filterBoxOpen) {
        changeCSS($filterBox, "display", ACHIEVEMENT_BTN.ORIGINAL_DISPLAY);
        changeCSS(
          $filterBtn,
          "background-color",
          ACHIEVEMENT_BTN.ORIGINAL_BACKGROUNDCOLOR
        );
      }
      else {
        changeCSS($filterBox, "display", ACHIEVEMENT_BTN.CLICKED_DISPLAY);
        changeCSS(
          $filterBtn,
          "background-color",
          ACHIEVEMENT_BTN.CLICKED_BACKGROUNDCOLOR
        );
      }
      setFilterBoxOpen(!filterBoxOpen);
    });

    // prevent event bubbling
    eventTo($filterBox, (event: Event) => event.stopPropagation());

  }, [filterBoxOpen]);

  return (
    <section className="filterBtnArea">
      <div className="filterBtnArea__percentBtn">
        <span>달성률</span>
        <FiChevronDown />
        <div className="filterBox">
          <div className="filterBox__selectArea">
            <h2>전체보기</h2>
            <h3>75% 이하</h3>
            <h3>75% ~ 100%</h3>
            <h3>100% 이상</h3>
          </div>
          <h3 className="filterBox__selfInputString">직접 입력</h3>
          <div className="filterBox__inputArea">
            <input type="number" max="9999"/>
            <span>%</span>
            <span>-</span>
            <input type="number" max="9999"/>
            <span>%</span>
          </div>
          <button type="button">입력값 적용</button>
        </div>
      </div>
      <div className="filterBtnArea__editorRecommendBtn">
        <span>에디터 추천</span>
        <HiX />
      </div>
    </section>
  );
}

export default BodyFilterBtnArea;
