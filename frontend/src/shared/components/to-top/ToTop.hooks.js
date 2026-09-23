// Pathing
// _______
// src/shared/components/to-top/ToTop.hooks.js

import { useEffect, useState } from 'react'

export const useScrollVisibility = (threshold = 500) => {
    const [isVisible, setIsVisible] = useState(false)

    useEffect(() => {
        const handleScroll = () => {
            setIsVisible(window.scrollY >= threshold)
        }

        handleScroll()
        window.addEventListener('scroll', handleScroll, { passive: true })

        return () => window.removeEventListener('scroll', handleScroll)
    }, [threshold])

    return isVisible
}
