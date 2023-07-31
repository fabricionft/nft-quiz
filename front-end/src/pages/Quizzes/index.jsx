import { useState } from 'react';
import Container from '../../components/Container'
import Quiz from '../../components/Quiz';
import styles from './Quizzes.module.css';
import useQuizzes from '../../hooks/useQuizzes';
import Loader from '../../components/Loader';
import useLoader from '../../hooks/useLoader';

export default function Quizzes(){

  const {quizzes, tag, inserirTag} = useQuizzes();
  const {loader} = useLoader();

  return(
    <Container>
      <header className={styles.acoesQuizzes}>
        <input type="number" className={styles.buscar} 
          placeholder='Digite a Tag do QUIZ'
          onChange={(e) => inserirTag(e.target.value)}
        />

        <details className={styles.info}>
          <summary>Como funciona o ranking</summary>
          <div className={styles.info}>
            <div className={styles.margemInfo}>
              <p className={styles.textoInfo}>Toda pergunta que for adcionada ou removida gera uma nova versão do quiz. Quando gerada, toda nova versão tem o seu ranking reinciado. Tal medida foi criada para tornar justo o ranqueamento, sem favorecer ou desfavorecer quem respondeu o quiz com mais ou menos questôes.</p>
            </div>
          </div>
        </details>
      </header>

      <div className={styles.containerQuizzes}>
        {quizzes.length ? (
          quizzes.slice().reverse().filter((quiz) => JSON.stringify(quiz.tag).includes(tag))
       
          .map((quiz) => <Quiz key={quiz.codigo} quiz={quiz} meuQuiz={false}/>)
          ) 
          : loader ? <Loader/>
          : <h1 className='aviso'>Sem quizzes no momento</h1>
        }
      </div>
    </Container>
  )
}