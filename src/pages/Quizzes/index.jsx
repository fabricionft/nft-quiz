import { useState } from 'react';
import Container from '../../components/Container'
import Quiz from '../../components/Quiz';
import styles from './Quizzes.module.css';
import useQuizzes from '../../hooks/useQuizzes';
import Loader from '../../components/Loader';
import useLoader from '../../hooks/useLoader';

export default function Quizzes(){

  const {quizzes} = useQuizzes();
  const [tag, setTag] = useState("");
  const {loader} = useLoader();

  return(
    <Container>
      <header className={styles.acoesQuizzes}>
        <input type="number" className={styles.buscar} 
          placeholder='Digite a Tag do QUIZ'
          onChange={(e) => setTag(e.target.value)}
        />
      </header>

      <div className={styles.containerQuizzes}>
        {quizzes.length ? (
          quizzes.slice().reverse().filter((quiz) => JSON.stringify(quiz.tag).includes(tag))
          .filter((quiz) =>  quiz.perguntas.length)
          .map((quiz) => <Quiz key={quiz.codigo} quiz={quiz} meuQuiz={false}/>)
          ) 
          : loader ? <Loader/>
          : <h1 className='aviso'>Sem quizzes no momento</h1>
        }
      </div>
    </Container>
  )
}