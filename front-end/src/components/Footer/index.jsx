import styles from './Footer.module.css';
import iconLinkedin from '../../assets/linkedin.png';
import iconGitHub from '../../assets/github.png';
import iconYoutube from '../../assets/youtube.png';
import iconWpp from '../../assets/wpp.png';

export default function Footer(){

  return(
    <footer className={styles.containerFooter}>
      <a href="https://www.linkedin.com/in/fabricionft/">
        <img src={iconLinkedin} className={styles.icon} />
      </a>

      <a href="https://github.com/fabricionft">
        <img src={iconGitHub} className={styles.icon} />
      </a>

      <a href="https://www.youtube.com/@fabricionft">
        <img src={iconYoutube} className={styles.icon} />
      </a>

      <a href="https://wa.me/5519984597236">
        <img src={iconWpp} className={styles.icon} />
      </a>
    </footer>
  )
}