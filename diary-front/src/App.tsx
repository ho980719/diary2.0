import {Route, Routes} from 'react-router-dom';
import Index from './pages';
import Header from './pages/layout/Header.tsx';
import {Container} from '@mui/material';
import Box from '@mui/material/Box';

function App() {
    return (
        <>
            <Box sx={{maxWidth: 1, bgcolor: 'background.paper'}}>
                <Header/>
                <Container sx={{mt: 4}} maxWidth="lg">
                    <Routes>
                        <Route path="/" element={<Index/>}/>
                    </Routes>
                </Container>
            </Box>
        </>
    );
}

export default App;
