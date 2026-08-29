// Pathing
// _______
// src/shared/components/return/Return.tsx

import styles from './Return.module.css'
import { useNavigate, useLocation } from 'react-router-dom'

const Return = () => {

    // ------------ SETUP ------------------------------------------------------

    const navigate = useNavigate()
    const location = useLocation()

    // ------------ EVENTS -----------------------------------------------------

    const handleBack = () => {

        // Blog Handle
        if (location.pathname.startsWith('/blog/')) {
            navigate('/')
            return
        }

        // Everything else (for now)
        if (window.history.length > 1) {
            navigate(-1)
        } else {
            navigate('/')
        }

    }

    // ------------ RETURN -----------------------------------------------------

    return (
        <button onClick={handleBack} className={styles.return}>
            <i className="fa fa-arrow-left" />
        </button>
    )

}

export default Return