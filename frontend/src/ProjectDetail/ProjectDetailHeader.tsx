import React from 'react';
import "./projectDetailHeader.scss";
import ImageSlider from './ImageSlider';

function ProjectDetailHeader() {
    return (
        <section className="projectDetailHeader">
            <section className="titleArea">
                <h4 className="titleArea__category">캐릭터 굿즈</h4>
                <h1 className="titleArea__title">울면 안 돼 근손실 나니까, 우리를 지켜줄 마동곰 키링</h1>
            </section>
            <section className="imageArea">
                image
            </section>
            <section className="infoArea">info</section>
        </section>
    )
}

export default ProjectDetailHeader;