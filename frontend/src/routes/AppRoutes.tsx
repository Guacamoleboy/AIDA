// Pathing
// _______
// src/routes/AppRoutes.tsx

import { Routes, Route } from 'react-router-dom'

const AppRoutes = () => (
    <Routes>

        {/* APP */}
        <Route element={<AppLayout />}>
            <Route path="/" element={<AppPage />} />
        </Route>

    </Routes>
)

export default AppRoutes