import Container from '../../components/Container';
import useSession from '../../hooks/useSession';
import { Link } from 'react-router-dom';

import imgQuiz from '../../assets/quiz.svg';

import styles from './Home.module.css';
import Loader from '../../components/Loader';

export default function Home(){

  const {sessao, codigo} = useSession();

  return(
    <Container centralizar={true}>
      <div className={styles.containerHome}>
        <div className={styles.inicial}>
          <h1 className={styles.slogan}>Crie seu quiz aqui</h1>
          <p className={styles.texto}>Crie, edite, e compartilhe o seu quiz da forma que quiser</p>

          <div className={styles.botoes}>
            <Link to={(sessao) ? "/quizzes" : "/cadastro"} className={styles.btnNavegar}>Responder quiz</Link>
            <Link to={(sessao) ? "/meusQuizzes/"+codigo : "/cadastro"} className={styles.btnNavegar}>Criar quiz</Link>
          </div>
        </div>

        <div className={styles.imgquiz}>
          <img src={imgQuiz} width="100%" />
        </div>
      </div>
    </Container>
  )
}