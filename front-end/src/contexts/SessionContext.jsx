import { createContext, useEffect, useState } from "react";

export const SessionContext = createContext();

export const Sessionprovider = ({children}) => {

  const [sessao, setSessao] = useState(() => {
    const sessao = localStorage.getItem('sessao');
    return (sessao) ? JSON.parse(sessao) : false;
  });

  useEffect(() =>{
    localStorage.setItem('sessao', JSON.stringify(sessao))
  }, [sessao])

  const codigo = sessao.codigo;
  const token = sessao.token

  const logar = (user) => setSessao(user);
  const deslogar = () => setSessao(false);

  return(
    <SessionContext.Provider value={{sessao, codigo, token, logar, deslogar}}>
      {children}
    </SessionContext.Provider>
  )
}