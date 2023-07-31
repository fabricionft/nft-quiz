import useSession from "../../hooks/useSession";
import useRotas from "../../hooks/useRotas";

export default function PrivatePage({children}){

  const {sessao} = useSession();
  const {bloquearRotaPrivada} = useRotas();

  bloquearRotaPrivada();

  return(
    <>
      {!sessao && null}
      {sessao && children}
    </>
  )
}