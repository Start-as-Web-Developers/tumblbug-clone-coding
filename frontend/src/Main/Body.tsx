import React, {useState,useEffect} from 'react';
import BodyFilterBtnArea from './BodyFilterBtnArea';
import BodyTitle from './BodyTitle';
import CardArea from './CardArea';
import "./body.scss";

type cardContent = {
  projectImg: string;
  projectIdx: number;
  category: string;
  createrName: string;
  title: string;
  comment: string;
  endDate: string;
  like: number;
  goalMoney: number;
  totalMoney: number;
};

function Body() {
  const [cards, setCards] = useState<cardContent[]>([]);

  useEffect(() => {
    fetch("https://zangsu-backend.store/projects/ongoing?sort=new")
      .then(response => response.json())
      .then((data: cardContent[]) => {
        setCards(prevCards => [...prevCards, ...data]);
      })
      .catch(error => {
        console.error("데이터를 가져오는 중 에러가 발생했습니다:", error);
      });
  }, []);

  return (
    <section className="body">
      <BodyFilterBtnArea />
      <BodyTitle cardNumber={cards.length}/>
      <CardArea cards={cards}/>
    </section>
  );
}

export default Body;