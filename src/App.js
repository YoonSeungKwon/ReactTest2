import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Header from './Components/Header';
import Main from './Components/Main';
import Login from './Components/Login';
import Register from './Components/Register';
import Test from './Components/Test';

const App = () => {
  return (
    <div>
        <Header />     
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Main />}></Route>
            <Route path="/login"    element={<Login    />}></Route>
            <Route path="/register" element={<Register />}></Route>
            <Route path="/test" element={<Test/>}></Route>
          </Routes>
        </BrowserRouter>
    </div>
  )
}

export default App;