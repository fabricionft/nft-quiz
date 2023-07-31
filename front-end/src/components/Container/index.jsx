import styles from './Container.module.css'
import MessageBox from '../MessageBox';

export default function Container({children, centralizar}){

  return(
    <>
      <MessageBox/>
      <div className={styles.containerPrincipal +" "+styles[(centralizar) && "centralizar"]}>
        <div className={styles.margemContainerPrincipal}>
          {children}
        </div>
      </div>
    </>
  )
}