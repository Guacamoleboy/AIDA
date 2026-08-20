// Pathing
// _______
// src/features/home-page/search-bar/SearchBar.tsx

import styles from './SearchBar.module.css'
import SearchButton from './SearchButton'
import SearchInput from './SearchInput'

const SearchBar = () => {
    return (
        <form className={styles.searchBarWrapper}>
            <SearchInput />
            <SearchButton />
        </form>
    )
}

export default SearchBar