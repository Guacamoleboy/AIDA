// Pathing
// _______
// src/app/layout/AppLayout.tsx

import { Outlet } from 'react-router-dom'
import Navbar from '@/shared/components/navbar/Navbar';
import Footer from '@/shared/components/footer/Footer';

function AppLayout() {
    return (
        <div className="appLayout">

            <Navbar />

            {/* Page Specific */}
            <main className="appMain">
                <Outlet />
            </main>

            <Footer />

        </div>
    )
}

export default AppLayout