import React, { useState, useEffect } from 'react';
import { changeCSS, eventTo, $ } from '../utils/commonFunction';
import "./imageSlider.scss";

interface imageFrame {
    src: string,
    altText: string
}

const isFirstPage = (currentPage: number):boolean => currentPage === 1;
const isLastPage = (currentPage: number, lastPage:number):boolean => currentPage === lastPage;
const setBtnDisAbled = ($target:HTMLElement, isDisAbled:boolean) => {
    const $targetAsBtnElement = $target as HTMLButtonElement;
    $targetAsBtnElement.disabled = isDisAbled;
}

// const rePaintSliderDot = ($currentDot: HTMLElement | null, currentPage:number, dotHandler:React.Dispatch<React.SetStateAction<HTMLElement | null>>) => {
    
// }

const paintCurrentDot = (currentPage:number):void => {
    const $dotContainer = $(".dotArea") as HTMLElement;
    const $dots = Array.from($dotContainer.children);
    const $currentDot = $dots[currentPage - 1] as HTMLInputElement;
    $currentDot.checked = true;
}

const giveLeftSliderEvent = ($target:HTMLElement, currentPage: number, pageHandler:React.Dispatch<React.SetStateAction<number>>):void => {
    const $imageContainer = $(".imageContainer") as HTMLElement;

    eventTo($target, () => {
        if (isFirstPage(currentPage)) {
            return;
        }
        changeCSS($imageContainer, "left", `${-100 * (currentPage - 2)}%`);
        pageHandler(currentPage - 1);
    });
}

const giveRightSliderEvent = ($target:HTMLElement, currentPage: number, lastPage: number, pageHandler:React.Dispatch<React.SetStateAction<number>>):void => {
    const $imageContainer = $(".imageContainer") as HTMLElement;

    eventTo($target, () => {
        if (isLastPage(currentPage, lastPage)) {
            return;
        }
        changeCSS($imageContainer, "left", `${-100 * (currentPage)}%`)
        pageHandler(currentPage + 1);
    });
}

function ImageSlider() {
    const [imageSliderPage, setImageSliderPage] = useState(1);
    const imageFrameArray: imageFrame[] = [
        {
            src: "https://img.danawa.com/prod_img/500000/032/872/img/6872032_1.jpg?shrink=330:*&_v=20210309150510",
            altText: "어피치"
        },
        {
            src: "https://t1.kakaocdn.net/kakaocorp/kakaocorp/admin/news/79590191017a00001.jpg?type=thumb&opt=C630x472.fwebp",
            altText: "라이언"
        },
        {
            src: "https://i.namu.wiki/i/UN24tGME7wFJPZjl6fERUB9XYkwSRCLt_8q_DWLV4WDdYGuwrunycLpcWrsdxjTcf24uEHoKqWgvblBkschDlA.webp",
            altText: "네오"
        }
    ];

    useEffect(() => {
        const $imageSliderLeftBtn = $(".imageSliderLeftBtn") as HTMLElement;
        const $imageSliderRightBtn = $(".imageSliderRightBtn") as HTMLElement;

        if (isFirstPage(imageSliderPage)) {
            setBtnDisAbled($imageSliderLeftBtn, true);
        }
        else if (isLastPage(imageSliderPage, imageFrameArray.length)) {
            setBtnDisAbled($imageSliderRightBtn, true);
        }
        else {
            setBtnDisAbled($imageSliderLeftBtn, false);
            setBtnDisAbled($imageSliderRightBtn, false);
        }

        giveLeftSliderEvent($imageSliderLeftBtn, imageSliderPage, setImageSliderPage);
        giveRightSliderEvent($imageSliderRightBtn, imageSliderPage, imageFrameArray.length, setImageSliderPage);
        paintCurrentDot(imageSliderPage);
    }, [imageSliderPage]);

    return (
        <section className="imageArea">
            <div className="leftBtnArea">
                <button type='button' className="imageSliderLeftBtn" disabled>{'<'}</button>
            </div>
            <div className="imageSliderArea">
                <div className="imageContainer" style={{width: `${imageFrameArray.length}00%`}}>
                    {
                        imageFrameArray.map(({src, altText}) => (
                            <div key={altText} style={{width: `${100 / imageFrameArray.length}%`}}>
                                <img src={src} alt={altText} />
                            </div>
                        ))
                    }
                </div>
            </div>
            <div className="rightBtnArea">
                <button type='button' className="imageSliderRightBtn">{'>'}</button>
            </div>
            <div className="dotArea">
                {
                    imageFrameArray.map(({src}, index) => 
                        index === 0 ? 
                            (<input key={src} type="radio" name="dot" checked/>) : 
                            (<input key={src} type="radio" name="dot"/> ) )
                }
            </div>
        </section>
    )
}

export default ImageSlider;