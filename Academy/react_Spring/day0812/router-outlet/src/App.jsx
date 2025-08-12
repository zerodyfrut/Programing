
import { Route, Routes } from 'react-router-dom'
import './App.css'
import SiteLayOut from './site/siteLayout'
import Home from './site/home/Home'
import Gallery from './site/gallery/gallery'
import Notice from './site/notice/notice'

function App() {

  return (
    <Routes>
      {/* 부모 라우트 : 레이아웃(공통 UI) */}
      <Route element={<SiteLayOut />}>
      {/* 자식 라우트 */}
        <Route path="/" element={<Home />} />
        <Route path="/gallery.go" element={<Gallery />} />
        <Route path="/notice.go" element={<Notice />} />
      </Route>
    </Routes>
  )
}

export default App;
