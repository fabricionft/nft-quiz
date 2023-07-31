import Container from '../../components/Container';
import useSession from '../../hooks/useSession';
import { Link } from 'react-router-dom';
import styles from './Home.module.css';

import imgQuiz from '../../assets/quiz.svg';
import imgResponsivo from '../../assets/responsivo.png';
import imgRapido from '../../assets/tempo-rapido.png';
import imgFacil from '../../assets/facil.png';
import imgLampada from '../../assets/lampada.png';

export default function Home(){

  const {sessao, codigo} = useSession();

  return(
    <Container centralizar={true}>
      <div className={styles.decoracao}></div>

      <div className={styles.containerHome}>
        <div className={styles.inicial}>
          <h1 className={styles.slogan}>Crie seu próprio quiz</h1>
          <p className={styles.texto}>Crie, edite, e compartilhe o seu quiz de forma simples e rápida com o NFT Quiz</p>

          <div className={styles.botoes}>
            <Link to={(sessao) ? "/meusQuizzes/"+codigo : "/cadastro"} className={styles.btnNavegar}>Criar quiz</Link>
            <Link to={(sessao) ? "/quizzes" : "/cadastro"} className={styles.btnNavegar}>Responder quiz</Link>
          </div>
        </div>

        <div className={styles.imgquiz}>
          <img src={imgQuiz} width="100%" />
        </div>

        <h2 className={styles.tituloVantagens}>Vantagens</h2>
        <div className={styles.vantagens}>
          <div className={styles.vantagem}>
            <img src={imgResponsivo} width={"40px"} />
            <p className={styles.textoVantagem}>Responsivo</p>
          </div>
          <div className={styles.vantagem}>
            <img src={imgRapido} width={"55px"} />
            <p className={styles.textoVantagem}>Rápido</p>
          </div>
          <div className={styles.vantagem}>
            <img src={imgFacil} width={"40px"} />
            <p className={styles.textoVantagem}>Fácil uso</p>
          </div>
          <div className={styles.vantagem}>
            <img src={imgLampada} width={"40px"} />
            <p className={styles.textoVantagem}>intuitivo</p>
          </div>
        </div>
      </div>
    </Container>
  )
}