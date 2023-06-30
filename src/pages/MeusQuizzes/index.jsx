import Container from "../../components/Container";
import useMeusQuizzes from "../../hooks/useMeusQuizzes";
import Quiz from "../../components/Quiz";
import styles from './MeusQuizzes.module.css';
import { Link } from "react-router-dom";
import { useState } from "react";
import useLoader from '../../hooks/useLoader';
import Loader from "../../components/Loader";

export default function MeusQuizzes(){

  const {meusQuizzes} = useMeusQuizzes();
  const [ordem, setOrdem] = useState('asc');
  const {loader} = useLoader();
  let quizzesOrdenados = (ordem == 'desc') ? meusQuizzes : meusQuizzes.slice().reverse();

  return(
    <Container>
      <header className={styles.acoesMeusQuizzes}>
        <Link to={"/criarQuiz"} className={styles.btnCriar}>Criar</Link>
        <select className={styles.ordem} onChange={() => setOrdem(ordem == 'asc' ? 'desc' : 'asc')}>
          <option>+ Recentes</option>
          <option>+ Antigos</option>
        </select>
      </header>

      <div className={styles.containerQuizzes}>
        { quizzesOrdenados.length 
          ? quizzesOrdenados.map(quiz => <Quiz key={quiz.codigo} quiz={quiz} meuQuiz={true}/>)
          : loader ? <Loader/>
          : <h1 className="aviso">Você ainda não possui quizzes</h1>
        }
      </div>
    </Container>
  )
}