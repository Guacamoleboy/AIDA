// Pathing
// _______
// src/features/blog-page/blog/BlogImage.tsx

import styles from './Blog.module.css'

// ------------------------------------------------------------------------------------------------------

const getBlogImageSize = (
    size: string | undefined,
) => {
    switch (size) {

        case 'xs':
            return styles.blogPictureXs

        case 's':
            return styles.blogPictureS

        case 'm':
            return styles.blogPictureM

        case 'l':
            return styles.blogPictureL

        case 'xl':
            return styles.blogPictureXl

        default:
            return undefined
    }
}

// ------------------------------------------------------------------------------------------------------

type BlogImageProps = {
    src?: string
    alt?: string
}

// ------------------------------------------------------------------------------------------------------

const BlogImage = ({
    src,
    alt,
}: BlogImageProps) => {

    return (
        <img
            src={src}
            alt=""
            className={getBlogImageSize(alt)}
        />
    )
}

export default BlogImage