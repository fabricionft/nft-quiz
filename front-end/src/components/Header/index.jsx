import styles from './Header.module.css';
import imgMenu from '../../assets/iconMenu.png';
import useSession from '../../hooks/useSession';
import { Link } from 'react-router-dom';

export default function Header(){

  const {sessao, codigo} = useSession();

  return(
    <header className={styles.containerHeader}>
      <div className={styles.margemHeader}>
        <label htmlFor="menu" className={styles.img}>
          <img src={imgMenu} className={styles.icon}/>
        </label>

        <div className={styles.links}>
            {sessao ? (
              <>
                <Link to={"/historico/"+codigo} className={styles.texto}>Histórico</Link>
                <Link to={"/meusQuizzes/"+codigo} className={styles.texto}>Meus quizzes</Link>
                <Link to={"/quizzes"} className={styles.texto}>Quizzes</Link>
              </>
            ) : (
              <>
                <Link to={"/cadastro"} className={styles.especial}>cadastre-se</Link>
                <Link to={"/login"} className={styles.especial}>Login</Link>
              </>
            )}
            <Link to={"/"} className={styles.texto}>Home</Link>
          </div>
        </div>
      </header>
  )
}