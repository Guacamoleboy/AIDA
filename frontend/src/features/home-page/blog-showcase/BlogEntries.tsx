// Pathing
// _______
// src/features/home-page/blog-showcase/BlogEntries.tsx

import { useEffect, useRef, useState } from 'react'
import type { ChangeEvent } from 'react'
import data from '@/shared/data/blog/meta.json'
import BlogEntry from './BlogEntry'
import styles from './BlogShowcase.module.css'

const BlogEntries = () => {

    const [currentPage, setCurrentPage] = useState(0)
    const [isMobile, setIsMobile] = useState(
        () => window.matchMedia('(max-width: 700px)').matches,
    )
    const blogEntriesRef = useRef<HTMLDivElement>(null)

    useEffect(() => {
        const mediaQuery = window.matchMedia('(max-width: 700px)')
        const updateMobileState = () => {
            setIsMobile(mediaQuery.matches)
            setCurrentPage(0)
            blogEntriesRef.current?.scrollTo({ left: 0 })
        }

        mediaQuery.addEventListener('change', updateMobileState)

        return () => mediaQuery.removeEventListener('change', updateMobileState)
    }, [])

    const pageSize = isMobile ? 4 : 8
    const blogEntryPages = data.reduce<(typeof data)[]>((pages, entry, index) => {
        const pageIndex = Math.floor(index / pageSize)

        if (!pages[pageIndex]) {
            pages[pageIndex] = []
        }

        pages[pageIndex].push(entry)

        return pages
    }, [])

    useEffect(() => {
        const blogEntries = blogEntriesRef.current

        if (!blogEntries) {
            return
        }

        const handleScroll = () => {
            const pageWidth = blogEntries.clientWidth

            if (pageWidth > 0) {
                setCurrentPage(Math.round(blogEntries.scrollLeft / pageWidth))
            }
        }

        blogEntries.addEventListener('scroll', handleScroll, { passive: true })

        return () => blogEntries.removeEventListener('scroll', handleScroll)
    }, [])

    const handlePageChange = (event: ChangeEvent<HTMLInputElement>) => {
        const page = Number(event.target.value)
        const blogEntries = blogEntriesRef.current

        setCurrentPage(page)
        blogEntries?.scrollTo({
            left: page * blogEntries.clientWidth,
            behavior: 'smooth',
        })
    }

    return (
        <div className={styles.blogCarousel}>

            {blogEntryPages.length > 1 && (
                <div className={styles.blogScrollbar}>
                    <input
                        type="range"
                        min="0"
                        max={blogEntryPages.length - 1}
                        step="1"
                        value={currentPage}
                        onChange={handlePageChange}
                        aria-label="Browse blog post pages"
                    />
                </div>
            )}

            <div
                ref={blogEntriesRef}
                className={styles.blogEntries}
            >
                <div
                    className={styles.blogEntryTrack}
                >
                    {blogEntryPages.map((entries, index) => (
                        <div
                            key={index}
                            className={styles.blogEntryPage}
                        >
                            {entries.map((entry) => (
                                <BlogEntry
                                    key={entry.id}
                                    id={entry.id}
                                    title={entry.title}
                                    description={entry.description}
                                    date={entry.date}
                                />
                            ))}
                        </div>
                    ))}
                </div>
            </div>

        </div>
    )
}

export default BlogEntries
