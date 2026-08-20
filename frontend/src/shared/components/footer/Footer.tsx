// Pathing
// _______
// src/shared/components/footer/Footer.tsx

import { Link } from 'react-router-dom'
import styles from './Footer.module.css'

const Footer = () => {
    return (
        <footer className={styles.footerWrapper}>

            {/* TOP ROW */}
            <p className={styles.credit}>
                Created by Guacamoleboy
            </p>

            {/* BOTTOM ROW */}
            <nav
                className={styles.navigation}
                aria-label="Footer navigation"
            >
                <Link to="/privacy">Privacy</Link>
                <Link to="/cookies">Cookies</Link>
                <Link to="/terms">Terms of Service</Link>
                <Link to="/law">Law</Link>
            </nav>
            
        </footer>
    )
}

export default Footer