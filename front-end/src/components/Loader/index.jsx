import styles from './Loader.module.css';

import imgLoaging from '../../assets/loading.gif';

export default function Loader(){

  return(
    <div className={styles.containerLoader}>
      <img src={imgLoaging} className={styles.loading} />
    </div>
  )
}