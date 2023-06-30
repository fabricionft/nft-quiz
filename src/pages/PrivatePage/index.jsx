import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import useSession from "../../hooks/useSession";

export default function PrivatePage({children}){

  const {sessao} = useSession();
  const navigate = useNavigate();

  useEffect(() => {
    if(!sessao) navigate("/");
  }, [sessao]);

  return(
    <>
      {!sessao && null}
      {sessao && children}
    </>
  )
}