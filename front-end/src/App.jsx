import { Outlet } from 'react-router-dom'
import Header from './components/Header'
import MenuBar from './components/MenuBar'
import PrivatePage from './pages/PrivatePage';
import Footer from './components/Footer';
import useRotas from './hooks/useRotas';

export default function App() {

  const {verificaSeAPaginaEPublica} = useRotas();
  
  return(
    <>
      <Header/>
      <MenuBar/>    
      {
        verificaSeAPaginaEPublica() ? <Outlet/>
        : (
          <PrivatePage>
            <Outlet/>
          </PrivatePage>
        )
      }
      <Footer/>
    </>
  )
}