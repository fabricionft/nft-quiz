import { Outlet } from 'react-router-dom'
import Header from './components/Header'
import MenuBar from './components/MenuBar'
import isPaginaPublica from './utils/IsPaginaPublica'
import PrivatePage from './pages/PrivatePage';
import Footer from './components/Footer';

export default function App() {
  
  return(
    <>
      <Header/>
      <MenuBar/>    
      {
        isPaginaPublica() ? <Outlet/>
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