// Pathing
// _______
// src/features/home-page/blog-showcase/BlogEntry.tsx

import { Link } from 'react-router-dom'
import type { BlogData } from '@/shared/types/types'
import styles from './BlogShowcase.module.css'

const BlogEntry = ({
    id,
    title,
}: BlogData) => {
    return (

        <Link
            to={`/blog/${id}`}
            className={styles.blogEntry}
        >

            {/* ICON */}
            <span
                className={styles.blogEntryIcon}
                aria-hidden="true"
            >
                <i className="fa fa-file-text" />
            </span>

            {/* TITLE */}
            <h3 className={styles.blogEntryTitle}>
                {title}
            </h3>

        </Link>
    )
}

export default BlogEntry