import React, { useState, useEffect, useRef } from "react";
import { FiChevronDown } from "react-icons/fi";
import { HiX } from "react-icons/hi";
import { EDITOR_FILTER_BTN, ACHIEVEMENT_BTN, FILTER_BOX, EDITOR_RECOMMEND_BOX } from "../utils/commonVariable";
import { $, changeCSS, changeMultiCSS, eventTo } from "../utils/commonFunction";
import "./bodyFilterBtnArea.scss";

const openFilterBox = (
  filterBtnRef: React.MutableRefObject<HTMLDivElement | null>,
  filterBoxRef: React.MutableRefObject<HTMLDivElement | null>,
  setFilterBoxOpen: React.Dispatch<React.SetStateAction<boolean>>
) => {
  const $filterBtn = filterBtnRef.current as HTMLElement;
  const $filterBox = filterBoxRef.current as HTMLElement;

  changeCSS($filterBox, "display", ACHIEVEMENT_BTN.CLICKED_DISPLAY);
  changeCSS(
    $filterBtn,
    "background-color",
    ACHIEVEMENT_BTN.CLICKED_BACKGROUNDCOLOR
  );
  setFilterBoxOpen(FILTER_BOX.OPEN);
};

const closeFilterBox = (
  filterBtnRef: React.MutableRefObject<HTMLDivElement | null>,
  filterBoxRef: React.MutableRefObject<HTMLDivElement | null>,
  setFilterBoxOpen: React.Dispatch<React.SetStateAction<boolean>>,
  filterPercentBtnText="달성률"
) => {
  const $filterBtn = filterBtnRef.current as HTMLElement;
  const $filterBox = filterBoxRef.current as HTMLElement;
  const $filterPercentBtn = $(".filterBtnArea__percentBtn > span") as HTMLElement;

  changeCSS($filterBox, "display", ACHIEVEMENT_BTN.ORIGINAL_DISPLAY);
  changeCSS(
    $filterBtn,
    "background-color",
    ACHIEVEMENT_BTN.ORIGINAL_BACKGROUNDCOLOR
  );
  setFilterBoxOpen(FILTER_BOX.CLOSE);
  $filterPercentBtn.innerHTML = filterPercentBtnText;
}

const openEditorRecommendBox = (
  $editorBtn: HTMLElement,
  $xBtn: SVGSVGElement,
  setEditorRecommending:React.Dispatch<React.SetStateAction<boolean>>
) => {
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
  setEditorRecommending(EDITOR_RECOMMEND_BOX.OPEN);
};

const clseEditorRecommendBox = (
  $editorBtn: HTMLElement,
  $xBtn: SVGSVGElement,
  setEditorRecommending: React.Dispatch<React.SetStateAction<boolean>>
) => {
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
  setEditorRecommending(EDITOR_RECOMMEND_BOX.CLOSE);
};

const setFilterInfoText = (isValid:boolean, setText:string) => {
  const $infoText = $(".filterBox__selfInputString") as HTMLElement;
  if(isValid) {
    changeCSS($infoText, "color", "#000");
  }
  else {
    changeCSS($infoText, "color", "#FF5757");
  }
  $infoText.innerHTML = setText;
}

const setFilterSearchBtn = (isClickable:boolean) => {
  const $filterSearchBtn = $(".filterBox__filterSearchBtn") as HTMLElement;
  if (isClickable) {
    changeCSS($filterSearchBtn, "color", "#fff");
    changeCSS($filterSearchBtn, "background-color", "#FA6462");
  }
  else {
    changeCSS($filterSearchBtn, "color", "#888");
    changeCSS($filterSearchBtn, "background-color", "#ECECEC");
    $filterSearchBtn.setAttribute("disabled", "true");
  }
}

const checkInputValue = (event:React.ChangeEvent<HTMLInputElement>) => {
  const $filterMinInput = $("#filterBox__minInput") as HTMLInputElement;
  const $filterMaxInput = $("#filterBox__maxInput") as HTMLInputElement;
  const $curInput = event.target as HTMLInputElement;
  const correctText = "올바른 입력입니다";

  let $otherInput: HTMLInputElement;
  let errorText: string;
  let comparison: boolean;

  if ($curInput === $filterMinInput) {
    $otherInput = $filterMaxInput;
    errorText = `${$otherInput.value}보다 작은 값을 입력해주세요`;
    comparison = false;
  }
  else {
    $otherInput = $filterMinInput;
    errorText = `${$otherInput.value}보다 큰 값을 입력해주세요`;
    comparison = true;
  }

  if(!$otherInput.value) {
    return;
  }

  const curNumberValue = parseInt($curInput.value, 10);
  const otherNumberValue = parseInt($otherInput.value, 10);

  if ((curNumberValue > otherNumberValue) === comparison) {
    setFilterInfoText(true, correctText);
    setFilterSearchBtn(true);
  }
  else {
    setFilterInfoText(false, errorText);
    setFilterSearchBtn(false);
  }
}

function BodyFilterBtnArea() {
  const [editorRecommending, setEditorRecommending] = useState(false);
  const [filterBoxOpen, setFilterBoxOpen] = useState(false);

  const filterBtnRef = useRef<HTMLDivElement | null>(null);
  const filterBoxRef = useRef<HTMLDivElement | null>(null);
  const editorBtnRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    const $filterBtns = document.querySelectorAll(".filterBox__selectArea > button");

    for(let i=0;i<$filterBtns.length;i+=1) {
      const $filterBtn = $filterBtns[i] as HTMLElement;
      eventTo($filterBtn, () => {
        closeFilterBox(filterBtnRef, filterBoxRef, setFilterBoxOpen, $filterBtn.innerHTML); 
      });
    }
  }, []);

  useEffect(() => {
    const $filterBtn = filterBtnRef.current as HTMLElement;
    const $filterBox = filterBoxRef.current as HTMLElement;
    const $filterBtnText = $("span", $filterBtn) as HTMLElement;

    eventTo($filterBtn, () => {
      if (filterBoxOpen) {
        closeFilterBox(
          filterBtnRef,
          filterBoxRef,
          setFilterBoxOpen,
          $filterBtnText.innerHTML
        );
      } else {
        openFilterBox(filterBtnRef, filterBoxRef, setFilterBoxOpen);
      }
    });

    eventTo($filterBox, (event: Event) => event.stopPropagation());
  }, [filterBoxOpen]);

  useEffect(() => {
    const $editorBtn = editorBtnRef.current as HTMLElement;
    const $editorXBtn = $editorBtn.querySelector("svg") as SVGSVGElement;

    eventTo($editorBtn, () => {
      if (editorRecommending) {
        clseEditorRecommendBox($editorBtn, $editorXBtn, setEditorRecommending);
      } else {
        openEditorRecommendBox($editorBtn, $editorXBtn, setEditorRecommending);
      }
    });
  }, [editorRecommending]);

  return (
    <section className="filterBtnArea">
      <div className="filterBtnArea__percentBtn" ref={filterBtnRef}>
        <span>달성률</span>
        <FiChevronDown />
        <div className="filterBox" ref={filterBoxRef}>
          <div className="filterBox__selectArea">
            <button type="button" className="filterBox__mainBtn">
              전체보기
            </button>
            <button type="button" className="filterBox__filterBtn">
              75% 이하
            </button>
            <button type="button" className="filterBox__filterBtn">
              75% ~ 100%
            </button>
            <button type="button" className="filterBox__filterBtn">
              100% 이상
            </button>
          </div>
          <h3 className="filterBox__selfInputString">직접 입력</h3>
          <div className="filterBox__inputArea">
            <input
              type="number"
              max="9999"
              id="filterBox__minInput"
              onChange={(event) => checkInputValue(event)}
            />
            <span>%</span>
            <span>-</span>
            <input
              type="number"
              max="9999"
              id="filterBox__maxInput"
              onChange={(event) => checkInputValue(event)}
            />
            <span>%</span>
          </div>
          <button type="button" className="filterBox__filterSearchBtn">
            입력값 적용
          </button>
        </div>
      </div>
      <div className="filterBtnArea__editorRecommendBtn" ref={editorBtnRef}>
        <span>에디터 추천</span>
        <HiX />
      </div>
    </section>
  );
}

export default BodyFilterBtnArea;
