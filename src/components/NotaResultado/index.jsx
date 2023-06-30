import styles from './NotaResultado.module.css';

export default function NotaResultado({porcentagem}){

  let nota = ((porcentagem / 10) == 10) ? porcentagem / 10 : (porcentagem / 10).toFixed(1); 

  return(
    <p className={styles.nota  +" "+ styles[(nota >= 5) ? "green" : "red"]}>{nota}</p>
  )
}