import Container from "../../components/Container";
import useMeusQuizzes from "../../hooks/useMeusQuizzes";
import Quiz from "../../components/Quiz";
import styles from './MeusQuizzes.module.css';
import { Link } from "react-router-dom";
import useLoader from '../../hooks/useLoader';
import Loader from "../../components/Loader";

export default function MeusQuizzes(){

  const {quizzesOrdenados, definirOrdem} = useMeusQuizzes();
  const {loader} = useLoader();

  return(
    <Container>
      <header className={styles.acoesMeusQuizzes}>
        <Link to={"/criarQuiz"} className={styles.btnCriar}>Criar</Link>
        <select className={styles.ordem} onChange={definirOrdem}>
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