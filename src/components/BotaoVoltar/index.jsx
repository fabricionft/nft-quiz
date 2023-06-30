import { Link } from "react-router-dom";
import styles from './BotaoVoltar.module.css';

import imgVoltar from '../../assets/iconVoltar.png';

export default function BotaoVoltar({caminho}){

  return (
    <header className={styles.cabecalho}>
      <div className={styles.margemCabecalho}>
        <Link to={caminho} className={styles.btnVoltar}>
        <img src={imgVoltar} className={styles.iconVoltar} />
        </Link>
      </div>
    </header>
  )
}