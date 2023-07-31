import styles from './NotaResultado.module.css';

export default function NotaResultado({porcentagem}){

  let nota = porcentagem / 10;

  return (
    <p className={styles.nota  +" "+ styles[(nota >= 5) ? "green" : "red"]}>
      {(nota == 10) ? nota : nota.toFixed(1)}
    </p>
  );
}