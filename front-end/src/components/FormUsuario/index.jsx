import { useState } from 'react';
import styles from './FormUsuario.module.css'
import { Link } from 'react-router-dom';

import imgUser from '../../assets/user.png';
import imgOlho from '../../assets/olho.png';
import imgOlhoFechado from '../../assets/olhoF.png';

export default function FormUsuario({executarAcao, txtBotao, prefixo, redirecionar}){

  const [usuario, setUsuario] = useState({});
  const [ver, setVer] = useState(false);

  const criarUsuario = (e) => {
    setUsuario({...usuario, [e.target.name] : e.target.value});
  }

  const enviarFormulario = (e) =>{
    e.preventDefault();
    executarAcao(usuario);
  }

  return(
    <form onSubmit={enviarFormulario} className={styles.form}>
      <div className={styles.margemForm}>
        <div className={styles.linha}>
          <input className={styles.inputFormUsuario} type="text" placeholder='Digite o usuário'
            name='usuario'
            onChange={criarUsuario}
          />

          <img src={imgUser} className={styles.iconOlho} onClick={() => setVer((ver) ? false : true)}/>
        </div>

        <div className={styles.linha}>
          <input className={styles.inputFormUsuario}type={(ver) ? "text" : "password"} placeholder='Digite a senha'
            name='senha'
            id='senha'
            onChange={criarUsuario}
          />

          <img src={(ver) ? imgOlhoFechado : imgOlho} className={styles.iconOlho} onClick={() => setVer((ver) ? false : true)}/>
        </div>

        <button className={styles.btnFormUsuario}>{txtBotao}</button>

        <Link to={redirecionar} className={styles.redirecionar}>{prefixo} tem conta?</Link>
      </div>
    </form>
  )
}