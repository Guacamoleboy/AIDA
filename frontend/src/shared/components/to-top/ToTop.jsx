// Pathing
// _______
// src/shared/components/to-top/ToTop.jsx

import { useScrollVisibility } from './ToTop.hooks'

const ToTop = () => {
    const isVisible = useScrollVisibility(500)

    const handleToTop = () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth',
        })
    }

    return (
        <button
            type="button"
            className={`toTop${isVisible ? ' toTopVisible' : ''}`}
            onClick={handleToTop}
            aria-label="Scroll to top"
            title="Scroll to top"
        >
            <i className="fa fa-arrow-up" aria-hidden="true" />
        </button>
    )
}

export default ToTop
