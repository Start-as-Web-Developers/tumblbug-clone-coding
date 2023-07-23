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
        console.error("�����͸� �������� �� ������ �߻��߽��ϴ�:", error);
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