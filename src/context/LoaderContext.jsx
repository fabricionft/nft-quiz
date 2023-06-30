import { useState } from "react";
import { createContext } from "react";

export const LoaderContext = createContext();

export const LoaderProvider = ({children}) => {

  const [loader, setLoader] = useState(true);

  const verificar = (dados) => (dados.length) ? setLoader(true) : setLoader(false);
  
  return(
    <LoaderContext.Provider value={{loader, verificar}}>
      {children}
    </LoaderContext.Provider>
  )
}