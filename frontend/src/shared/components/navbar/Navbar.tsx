// Pathing
// _______
// src/shared/components/navbar/Navbar.tsx

import styles from './Navbar.module.css'

const Navbar = () => {
    return (
        <header className={styles.navbarWrapper}>

            {/* LEFT */}
            <h1 className={styles.title}>
                AIDA - AI Drevne Applikationer
            </h1>

            {/* RIGHT */}
            <a
                className={styles.githubLink}
                href="https://github.com/guacamoleboy"
                target="_blank"
                aria-label="github"
            >
                <i className="fa fa-github" aria-hidden="true" />
            </a>
            
        </header>
    )
}

export default Navbar